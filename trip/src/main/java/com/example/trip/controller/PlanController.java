package com.example.trip.controller;

import com.example.trip.entity.Accommodation;
import com.example.trip.entity.Flight;
import com.example.trip.entity.ThingsTodo;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private List planItemList = new ArrayList<>();

    @Autowired
    private FlightController flightController;
    @Autowired
   private AccommodationController accommodationController;

    @Autowired
    private ThingsTodoController thingsTodoController;

    @Autowired
    private UserController userController;


    /*
    * Users with different choices will get different recommendation.
    * Mode "1": things_todo
    * Mode "2": things_todo + accommodation
    * Mode "3": things_todo + accommodation + flights
        * For accommodations, it will return 3,5,7 lists of hotels to users with different budgets
    * */
    @GetMapping("/list")
    public HashMap<Integer, List> planList(@RequestParam("startDay") String startDay, @RequestParam("endDay") String endDay,
                         @RequestParam("home") String home, @RequestParam("des") String des, // home value can only be: "SYD", "BNE", "MEL", des value: sydney, brisbane, melbourne
                         @RequestParam("budget") String budget, @RequestParam("style") String[] style, @RequestParam("group") String group,
                         @RequestParam("mode") String mode) throws AnalysisException, NetworkException, ParseException {


        // reset
        HashMap<Integer, List> planMap = new HashMap<Integer, List>();

        // calculate duration
        Date dateAfter=new SimpleDateFormat("yyyy-MM-dd").parse(startDay);
        Date dateBefore=new SimpleDateFormat("yyyy-MM-dd").parse(endDay);
        long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
        int duration = (int) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS)+1;
        // System.out.println("duration: "+duration);

        // track day, number of rows found by sql query
        int j = 1;
        int num;

        // match things_todo
        List<ThingsTodo> thingsList = thingsTodoController.conditionSearch(des,budget,style);

        // Assign to per day
        num = thingsList.size();
        System.out.println("number of things found: " + num);

        for(int i=0; i<num; i++) {
            if(j>duration) {
                System.out.println("larger j: "+j);
                j = 1;
            }

            List temp;
            if(planMap.get(j) == null) {
                temp = new ArrayList<ThingsTodo>();
            } else {
                temp = planMap.get(j);
            }
            temp.add(thingsList.get(i));
            // Add to return map
            planMap.put(j,temp);
            j++;

        }

        j = 1; // reset j for later

        // detect which mode (need hotels/flights)
        Integer mod = Integer.parseInt(mode);

        Integer groupSize = Integer.parseInt(group);

        if(mod > 1) {
            // match accommodation
            List<Accommodation> accommodationList = accommodationController.conditionSearch(des,budget,group);

            // Assign to per day
            num = accommodationList.size();

            // if limited accommodation, extend the list
            int diff = Math.abs(num-duration);
            for(int i=0; i<diff; i++) {
                if(i >= 2) {
                    accommodationList.add(2,accommodationList.get(3));
                } else {
                    accommodationList.add(0, accommodationList.get(0));
                }
            }

            for(int i=1; i<duration; i++) {
                List storedThings = new ArrayList<>();
                if(planMap.get(j) != null) {
                    storedThings = planMap.get(j);
                }
                
                if(j <= 2 & (i-1) < num) {
                    storedThings.add(accommodationList.get(i-1));


                } else if (j <= 4 ) {
                    storedThings.add(accommodationList.get((int)Math.floor(num/2)));

                } else {
                    storedThings.add(accommodationList.get(num-1));

                }
                planMap.put(j,storedThings);
                j++;
            }
            j = 1; // reset j for later

            if(mod == 3) {
                // match flights
                List<Flight> flightList = flightController.conditionSearch(startDay,endDay,home,des,budget);
                if(flightList.size() < 2) {
                    return planMap;
                }

                List startPlan = planMap.get(1);
                startPlan.add(flightList.get(0));
                planMap.put(1,startPlan);

                if(planMap.get(duration) == null) {
                    List ls = new ArrayList<>();
                    ls.add(flightList.get(flightList.size()-1));
                    planMap.put(duration, ls);
                } else {
                    List endPlan = planMap.get(duration);
                    endPlan.add(flightList.get(flightList.size() - 1));
                    planMap.put(duration, endPlan);
                }
            }
        }

        return planMap;

    }

}

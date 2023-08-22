package com.example.trip.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.trip.entity.Accommodation;
import com.example.trip.service.AccommodationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;
    @RequestMapping("/list")
    public List AccommodationList(){
        return  accommodationService.list();
    }
    @PostMapping
    public boolean saveOrUpdate(@RequestBody Accommodation accommodation){
        return accommodationService.saveOrUpdate(accommodation);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return accommodationService.removeById(id);
    }
    @GetMapping("/page")
    public IPage<Accommodation> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam(defaultValue = "")String name, @RequestParam(defaultValue = "") String cityName){
        IPage<Accommodation> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Accommodation> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name),"name",name);
        queryWrapper.like("city_name",cityName);
        return accommodationService.page(page,queryWrapper);
    }

    @RequestMapping("/search")
    public List<Accommodation> conditionSearch(String des, String budget, String group){ // des value: sydney, brisbane, melbourne

        Integer groupSize = Integer.parseInt(group);
        QueryWrapper<Accommodation> hotelQuery = new QueryWrapper<>();
        hotelQuery.like("city_name",des.substring(1)).ge("capacity", groupSize).le("capacity", groupSize+1);
        switch(budget) {
            case "lowBudget":
                hotelQuery.orderByAsc("price").last("limit 3");
                break;
            case "mediumBudget":
                hotelQuery.orderByAsc("price").last("limit 5");
                break;
            case "highBudget":
                hotelQuery.orderByDesc("price").last("limit 7");
                break;
        }

        return accommodationService.list(hotelQuery);
    }
}

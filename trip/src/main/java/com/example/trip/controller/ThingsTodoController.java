package com.example.trip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.trip.entity.ThingsTodo;
import com.example.trip.service.ThingsTodoService;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/things")
public class ThingsTodoController {
   @Autowired
   private ThingsTodoService thingsTodoService;

   private TextExtractor textExtractor;

   @RequestMapping("/list")
   public List ThingsTodoList(){
      return  thingsTodoService.list();
   }
   @RequestMapping("/attractionlist")
   public List AttractionList(){
      QueryWrapper<ThingsTodo>queryWrapper=new QueryWrapper<>();
      queryWrapper.eq("type_","Attraction");
      return  thingsTodoService.list(queryWrapper);
   }
   @RequestMapping("/listbytype")
   public List GetListByType( @RequestParam String[] types){
      QueryWrapper<ThingsTodo>queryWrapper=new QueryWrapper<>();
      for (String type : types) {
         queryWrapper.eq("type_",type).or();
      }


      return  thingsTodoService.list(queryWrapper);
   }

   @DeleteMapping("/{id}")
   public boolean delete(@PathVariable Integer id)
   {
      return thingsTodoService.removeById(id);
   }

   @GetMapping("/page")
   public IPage<ThingsTodo> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize
           , @RequestParam(defaultValue = "")String type, @RequestParam(defaultValue = "") String city){
      IPage<ThingsTodo> page=new Page<>(pageNum,pageSize);
      QueryWrapper<ThingsTodo> queryWrapper=new QueryWrapper<>();
      queryWrapper.like(Strings.isNotEmpty(type),"type",type);
      queryWrapper.like("city",city);
      return thingsTodoService.page(page,queryWrapper);
   }

   /*
   * des value: sydney, brisbane, melbourne
   * style: ["Attraction","Activity","Food And Drink","Water Sports"]
   * textExtractor.textAnalysis(desc): match description with a style
   * return: (Recommendation of) Things to do items that match the selected styles
   */
   @RequestMapping("/search")
   public List<ThingsTodo> conditionSearch(String des, String budget, String[] style) throws AnalysisException, NetworkException {

      textExtractor = new TextExtractor();

      QueryWrapper<ThingsTodo> thingsQuery = new QueryWrapper<>();
      thingsQuery.like("city_",des.substring(1));

      if(budget.equals("highBudget")) {
         thingsQuery.orderByDesc("price_");
      } else if(budget.equals("lowBudget")){
         thingsQuery.orderByAsc("price_");
      }

      // If user hasn't chosen any styles: recommend randomly.
      if(style.length == 0) {
         thingsQuery.eq("type_", "Attraction").or().eq("type_", "Tour").or().eq("type_", "Food And Drink").last("limit 15");
         return thingsTodoService.list(thingsQuery);
      }

      // If user has chosen styles
      else {
         thingsQuery.last("limit 15");
         List<ThingsTodo> queryRes = new ArrayList<>();
         List<ThingsTodo> retList = new ArrayList<>();
         queryRes.addAll(thingsTodoService.list(thingsQuery));


         for (ThingsTodo t : queryRes) {
            String desc = t.getDesc();
            HashSet<String> styleDetected = textExtractor.textAnalysis(desc);

            for (String s : styleDetected) {
               if (Arrays.asList(style).contains(s)) {
                  if(!retList.contains(t)) {
                     retList.add(t);
                  }
               }
            }
         }
         //return queryRes;//.subList(0,8); // static for debug
         return retList;
      }

   }

}

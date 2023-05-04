package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Event
{
    public Event(String name, String city)
    {
        this.name = name;
        this.city = city;
    }
    private String name;
    private String city;

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }
}

@Controller
public class EventsController {
    static final ArrayList<Event> events = new ArrayList<Event>(){{
        add(new Event("Violin concert", "Prague"));
        add(new Event("Jazz concert", "Vienna"));
        add(new Event("Art exhibition", "Munich"));
        add(new Event("Art-house exhibition", "Amsterdam"));
        add(new Event("Requiem Verdi concert", "Berlin"));
    }};

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String listEventsChangeName(@RequestParam(name="city", required = false, defaultValue = "all") String city, Model model)
    {
        List<Event>result = events;
        if(!city.equals("all")){
            result= events.stream().filter(e -> e.getCity().equals(city)).collect(Collectors.toList());
        }
        model.addAttribute("events", result);
//        List<Event> cityEvents = events.stream().filter(e -> e.getCity().equals(city)).collect(Collectors.toList());
//        model.addAttribute("events", cityEvents); // Pass data to view
        return "eventsview"; // Return name of view
    }
}



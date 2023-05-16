package com.example.servingwebcontent;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        add(new Event("Opera", "London"));
        add(new Event("Violin concert", "Prague"));
        add(new Event("Jazz concert", "Berlin"));
        add(new Event("Art exhibition", "London"));
    }};

    @Operation(summary = "Get all events", description = "Get all events filtered by city")
    @RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Event> listEventsChangeName(@RequestParam(name = "city", required = false, defaultValue = "all") String city, Model model) {
        List<Event> result = events;
        if (!city.equals("all")) {
            result = events.stream().filter(e -> e.getCity().equals(city)).collect(Collectors.toList());
        }
        return result;
    }

    @Operation(summary = "Get  events", description = "Get specify event by id")
    @GetMapping(value = "/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable int id)
    {
        Event event = events.get(id);
        return event;
    }

    @Operation(summary = "Post  events", description = "Create event")
    @PostMapping(value = "/events")
    @ResponseBody
    public Event createEvent(@RequestBody Event event)
    {
        // TODO save to database
        return event;
    }
    @Operation(summary = "Delete  events", description = "Delete event by id")
    @DeleteMapping(value = "/events/{id}")
    @ResponseBody
    public Event deleteEvent(@PathVariable int id)
    {
        Event event = events.get(id);
        // TODO: remove it from the database
        return event;
    }

    @PutMapping(value = "/events/{id}")
    @ResponseBody
    public Event updateEvent(@PathVariable int id, @RequestBody Event event)
    {
        Event origEvent = events.get(id);
        // TODO: update event in the database
        origEvent = event; // useless just for example
        return event;
    }

}

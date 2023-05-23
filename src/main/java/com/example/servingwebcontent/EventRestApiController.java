package com.example.servingwebcontent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("rest-api-events")   // @RestController = @Controller + @ResponseBody for each methode
@Tag(name = "Events", description = "Working with events")
public class EventRestApiController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Get all events.", description = "Get all events filtered by city.")
    @GetMapping
    //@ResponseBody
    public List<EventDTO> listEvents(@RequestParam(
            value = "city", required = false, defaultValue = "all") String city, Model model) {
        
        return eventService.getAllEvents(city);
    }
    
    @Operation(summary = "Get event by ID.", description = "Get specific event by entering event ID.")
    @GetMapping(value = "/{eventId}")
    //@ResponseBody
    public EventDTO getEvent(@PathVariable int eventId) {

        return eventService.getEventById(eventId);
    }
   
    @Operation(summary = "Delete event by ID.", description = "Delete specific event by entering event ID.")
    @DeleteMapping(value = "/{eventId}")
    public EventDTO deleteEvent(@PathVariable int eventId) {

        return eventService.deleteEventById(eventId);
    }

    @Operation(summary = "Update event fields.", description = "Update event fields by entering new values.")
    @PutMapping(value = "/{eventId}")
    public EventDTO updateEvent(@PathVariable int eventId, @RequestBody EventDTO newEventDTO) {

        return eventService.updateEventById(eventId, newEventDTO);
    }

    @Operation(summary = "Add new event.", description = "Add new event by entering object fields.")
    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {

        return eventService.createNewEvent(eventDTO);
    }

}

package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Artist {
    private String name;
    private String genre;

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre()
    {
        return genre;
    }
}

@Controller
public class ArtistsController {
    static final ArrayList<Artist> artists = new ArrayList<Artist>(){{
        add(new Artist("Johann Sebastian Bach, Berliner Philharmonic ", " classic music"));
        add(new Artist("James Blake ", " alternative"));
        add(new Artist("Rammstein ", " rock"));
        add(new Artist("K.I.Z.", " hip hop music"));
    }};

    @RequestMapping(value = "/artists", method = RequestMethod.GET)
    public String listArtists(@RequestParam(value = "genre", required = false, defaultValue = "all") String genre, Model model)
    {
        List<Artist> resultArtists = artists;
        if (!genre.equals("all")) {
            resultArtists = artists.stream().filter(e -> e.getGenre().equals(genre)).collect(Collectors.toList());
        }
        model.addAttribute("artists", resultArtists);
        return "artistsView";
    }
}

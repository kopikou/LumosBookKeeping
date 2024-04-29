package org.example;

import org.example.Lumos.entity.People;
import org.example.Lumos.entity.ShowProgram;
import org.example.Lumos.services.PeopleService;
import org.example.Lumos.services.ShowProgramService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShowProgramService showProgramService = new ShowProgramService();
        List<ShowProgram> showPrograms = showProgramService.findAllShowPrograms();
        for(ShowProgram showProgram:showPrograms){
            System.out.println(showProgram);
        }
        PeopleService peopleService = new PeopleService();
        List<People> people = peopleService.findAllPeople();
        for(People human:people){
            System.out.println(human);
        }

        /*System.out.println("ДОХОДЫ");
        System.out.println("01/01/2024");
        System.out.println(showProgramService.findShowProgram(3).getPrice());
        System.out.println(showProgramService.findShowProgram(3).getTitle());

        System.out.println("РАСХОДЫ");
        System.out.println("01/01/2024");
        System.out.println(peopleService.findPeople(7).getName());
        System.out.println(showProgramService.findShowProgram(3).getArtistSalary());*/


    }
}
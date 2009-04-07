package com.om.example.dvr.domain;

import java.util.LinkedList;
import java.util.List;

public class SeasonPassManager {
   private final Schedule schedule;
   private List<Program> toDoList = new LinkedList<Program>();

   public SeasonPassManager(Schedule schedule) {
      this.schedule = schedule;
   }

   public int sizeOfToDoList() {
      return toDoList.size();
   }

   public Program createNewSeasonPass(String programName, int channel) {
      List<Program> programsFound = schedule.findProgramsNamedOn(programName, channel);

      for (Program current : programsFound)
         if (!alreadyInToDoList(current))
            toDoList.add(current);

      if (programsFound.size() > 0)
         return programsFound.get(0);
      return null;
   }

   private boolean alreadyInToDoList(Program candidate) {
      for (Program current : toDoList)
         if (current.sameEpisodeAs(candidate))
            return true;

      return false;
   }

   public Iterable<?> toDoListIterator() {
      return toDoList;
   }

   public List<Program> toDoListContentsFor(String programId) {
      List<Program> result = new LinkedList<Program>();

      for (Program current : toDoList)
         if (current.getId().equals(programId))
            result.add(current);

      return result;
   }
}
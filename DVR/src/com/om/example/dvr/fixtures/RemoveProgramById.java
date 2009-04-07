package com.om.example.dvr.fixtures;

public class RemoveProgramById {
   private String id;

   public RemoveProgramById() {
   }

   public RemoveProgramById(String id) {
      this.id = id;
      execute();
   }

   public void setId(String id) {
      this.id = id;
   }

   public void execute() {
      AddProgramsToSchedule.getSchedule().removeProgramById(id);
   }
}
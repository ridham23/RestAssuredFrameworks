package com.qa.Client;

public class users {

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public   String name;
   public String job;
   public String createdAt;
   public  String id;
   public users()
   {

   }
    public users(String name,String job)
    {
this.name=name;
this.job=job;
    }

}

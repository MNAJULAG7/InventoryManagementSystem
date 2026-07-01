package com.inventory.sales.exception;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException(String name,Long id)
    {
        super(name+" cannot be identified by the give id "+id);
    }

    public  ResourceNotFoundException(String modelName,String name)
    {
        super(modelName+" cannot be identified by the give name "+name);
    }
}

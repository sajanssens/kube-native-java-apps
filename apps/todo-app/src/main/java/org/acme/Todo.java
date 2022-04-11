package org.acme;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity 
public class Todo extends PanacheEntity { 

    @NotBlank 
    @JsonSetter("activity")
    public String title;
    public boolean completed;

    @Column(name = "ordering")
    public int order;

    @JsonSetter("link")
    public String url;

    public static List<Todo> findNotCompleted() { 
        return list("completed", false);
    }

    public static List<Todo> findCompleted() {
        return list("completed", true);
    }

    public static long deleteCompleted() {
        return delete("completed", true);
    }

}
package org.test.blogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name="Ricette")
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String title;
    private String listAndQuantities;
    private String img;
    private LocalDate cookingTime;
    private int portions;
    private String cooking;

    //getter & setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListAndQuantities() {
        return listAndQuantities;
    }

    public void setListAndQuantities(String listAndQuantities) {
        this.listAndQuantities = listAndQuantities;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDate getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(LocalDate cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }
}

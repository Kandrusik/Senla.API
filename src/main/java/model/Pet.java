package model;

import utilsAPI.Status;

import java.util.List;

public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<TagPet> tags;
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<TagPet> getTags() {
        return tags;
    }

    public void setTags(List<TagPet> tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public Pet(Integer id, Category category, String name, List<String> photoUrls, List<TagPet> tags, Status status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }
}

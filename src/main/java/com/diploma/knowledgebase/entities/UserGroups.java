package com.diploma.knowledgebase.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_groups", schema = "public")
public class UserGroups {
    @JsonIgnore
    private long id;
    private String groupName;
    private String description;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "group_name", nullable = false)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "UserGroups{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroups that = (UserGroups) o;
        return id == that.id &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, description);
    }
}

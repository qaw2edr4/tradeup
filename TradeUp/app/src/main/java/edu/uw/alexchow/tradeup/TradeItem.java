package edu.uw.alexchow.tradeup;

import android.location.Location;

/**
 * Created by alexchow on 5/23/16.
 */
public class TradeItem {
    public String id, name, posterName, image, status, title, description, timeStamp;
    public Location location;


    public TradeItem() {
    }

    public TradeItem(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TradeItem(String id, String name, String posterName, String image, String status,
                     String title, String description, String timeStamp, Location location) {
        super();
        this.id = id;
        this.name = name;
        this.posterName = posterName;
        this.image = image;
        this.status = status;
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getImge() {
        return image;
    }

    public void setImge(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sighting {

    private int animal_id;
    private int sighting_id;
    private String location;
    private String ranger_name;

    public Sighting(int animal_id, String location, String ranger_name) {
        this.animal_id = animal_id;
        this.location = location;
        this.ranger_name = ranger_name;
    }

    public int getAnimalId() {
        return animal_id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return ranger_name;
    }

    public int getSightingId() {
        return this.sighting_id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, location, ranger_name) VALUES (:animal_id, :location, :ranger_name);";
            this.sighting_id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int sighting_id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where sighting_id = :sighting_id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("sighting_id", sighting_id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return Objects.equals(ranger_name, sighting.ranger_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranger_name);
    }
}

package models;

import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Animal {

    private  String health;
    private String age;
    public static final boolean DATABASE_ENDANGERED = true;

    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        endangered = DATABASE_ENDANGERED;
    }

    public String getHealth() {
        return this.health;
    }

    public String getAge() {
        return age;
    }


    public void saveEndangeredAnimal() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, endangered, health, age) VALUES (:name, :endangered, :health, :age);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE endangered = true;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id = :id";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        }
    }
}

package models;

import org.sql2o.Connection;

import java.util.List;

public class CommonAnimal extends Animal {

    public static final boolean DATABASE_ENDANGERED = false;

    public CommonAnimal(String name) {
        this.name = name;
        endangered = DATABASE_ENDANGERED;
    }


    public void saveCommonAnimal() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static List<CommonAnimal> all() {
        String sql = "SELECT * FROM animals WHERE endangered = false;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(CommonAnimal.class);
        }
    }

    public static CommonAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id = :id";
            CommonAnimal commonAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(CommonAnimal.class);
            return commonAnimal;
        }
    }
}
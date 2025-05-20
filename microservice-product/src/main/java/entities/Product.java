package entities;

public class Product {
    private Long id;
    private String name;
    private int stock;
    private Long idCategory;
    private Long idWareHouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdWareHouse() {
        return idWareHouse;
    }

    public void setIdWareHouse(Long idWareHouse) {
        this.idWareHouse = idWareHouse;
    }
}

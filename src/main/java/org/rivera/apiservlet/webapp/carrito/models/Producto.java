package org.rivera.apiservlet.webapp.carrito.models;

public class Producto {
  private Long id;
  private String name;
  private String type;
  private int price;

  public Producto() {
  }

  public Producto(Long id, String name, String type, int price) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.price = price;
  }

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}

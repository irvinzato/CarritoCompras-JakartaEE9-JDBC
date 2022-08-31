package org.rivera.apiservlet.webapp.carrito.models;

import java.time.LocalDate;

public class Producto {
  private Long id;
  private String name;
  private int price;
  private LocalDate registerDate;
  private Categoria category;
  private String sku;


  public Producto() {
  }

  public Producto(Long id, String name, String type, int price) {
    this.id = id;
    this.name = name;
    Categoria c = new Categoria();
    c.setName(type);
    this.category = c;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public LocalDate getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(LocalDate registerDate) {
    this.registerDate = registerDate;
  }

  public Categoria getCategory() {
    return category;
  }

  public void setCategory(Categoria category) {
    this.category = category;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }
}

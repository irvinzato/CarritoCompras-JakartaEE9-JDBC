package org.rivera.apiservlet.webapp.carrito.models;

import java.util.Objects;

public class ItemCarro {
  private int amount;
  private Producto product;

  public ItemCarro(int amount, Producto product) {
    this.amount = amount;
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Producto getProduct() {
    return product;
  }

  public void setProduct(Producto product) {
    this.product = product;
  }

  public int getTotalPrice() {
    return this.amount * this.product.getPrice();
  }

  //Comparo el objeto por "id" y "nombre" para no duplicar líneas con el mismo producto, solo la cantidad
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemCarro itemCarro = (ItemCarro) o;
    return Objects.equals(product.getId(), itemCarro.product.getId())
            && Objects.equals(product.getName(), itemCarro.product.getName());
  }

}

package org.rivera.apiservlet.webapp.carrito.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
  private List<ItemCarro> items;

  public Carro() {
    this.items = new ArrayList<>();
  }
  //La línea debe ser única, por ello debo ver si ya fue añadido el item
  public void addItemCar( ItemCarro itemCar ) {
    //Debo implementar el "equals" para el objeto, me interesa comparar por "id" y "nombre"
    if( items.contains(itemCar) ) {
      Optional<ItemCarro> optionalItemCarro = items.stream()
              .filter(i -> i.equals(itemCar))
              .findAny();
      if( optionalItemCarro.isPresent() ) { //Modifico la cantidad del item que ya está
        ItemCarro ic = optionalItemCarro.get();
        ic.setAmount( ic.getAmount() + 1 );
      }
    }else {
      this.items.add(itemCar);
    }
  }

  public List<ItemCarro> getItems() {
    return items;
  }

  //Calculo precio total de todos los artículos
  public int getTotal() {
    return items.stream()
            .mapToInt( i -> i.getTotalPrice() ).sum();
  }

  public void removeProductos(List<String> productoIds) {
    if (productoIds != null) {
      productoIds.forEach(this::removeProducto);
      // que es lo mismo a:
      // productoIds.forEach(productoId -> removeProducto(productoId));
    }
  }

  public void removeProducto(String productoId) {
    Optional<ItemCarro> item = findProducto(productoId);
    item.ifPresent(itemCarro -> items.remove(itemCarro));
  }

  private Optional<ItemCarro> findProducto(String productoId) {
    return  items.stream()
            .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProduct().getId())))
            .findAny();
  }

  public void updateCantidad(String productoId, int cantidad) {
    Optional<ItemCarro> item = findProducto(productoId);
    item.ifPresent(itemCarro -> itemCarro.setAmount(cantidad));
  }

}

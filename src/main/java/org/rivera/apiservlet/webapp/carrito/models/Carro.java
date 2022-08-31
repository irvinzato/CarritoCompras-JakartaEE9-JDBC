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
}

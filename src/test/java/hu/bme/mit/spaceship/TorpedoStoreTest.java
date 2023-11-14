package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

  @Test
  void fire_Success(){
    // Arrange
    TorpedoStore store = new TorpedoStore(1);

    // Act
    boolean result = store.fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  void fire_Failrule_with_count(){
    // Arrange
    TorpedoStore store = new TorpedoStore(3, 1);

    // Act
    boolean result = store.fire(1);
    int countResult = store.getTorpedoCount();
    //Assert
    assertEquals(false, result);
    assertEquals(3, countResult);
  }

  @Test
  void fire_Count_and_not_empty(){
    // Arrange
    TorpedoStore store = new TorpedoStore(3, 0);

    // Act
    store.fire(1);
    int result = store.getTorpedoCount();
    boolean empty = store.isEmpty();

    //Assert
    assertEquals(2, result);
    assertEquals(false, empty);
  }

  @Test
  void fire_illegal_argument(){
    TorpedoStore store = new TorpedoStore(1, 0);
    
    assertThrows(IllegalArgumentException.class, () -> {
      store.fire(10);
    });
        assertThrows(IllegalArgumentException.class, () -> {
      store.fire(-1);
    });
  }

  @Test
  void inv_rate_from_env_variable_1(){
    EnvVariableProvider.test.put("INV_RATE", "1.0");

    TorpedoStore store = new TorpedoStore(1);
    assertEquals(store.fire(1), false);
    EnvVariableProvider.test.clear();
  }

  @Test
  void inv_rate_from_env_variable_2(){
    EnvVariableProvider.test.put("INV_RATE", "0.0");

    TorpedoStore store = new TorpedoStore(1);
    assertEquals(store.fire(1), true);
    EnvVariableProvider.test.clear();
  }
  @Test
  void inv_rate_from_env_variable_3(){
    EnvVariableProvider.test.put("INV_RATE", "invalid double");

    TorpedoStore store = new TorpedoStore(1);
    assertEquals(store.fire(1), true);
    EnvVariableProvider.test.clear();
  }
}

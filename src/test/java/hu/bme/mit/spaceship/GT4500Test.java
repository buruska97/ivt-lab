package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore ts1;
  private TorpedoStore ts2;


  @BeforeEach
  public void init(){

      this.ts1 = mock(TorpedoStore.class);
      this.ts2 = mock(TorpedoStore.class);
      ship = new GT4500(this.ts1, this.ts2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(ts1, times(1)).fire(1);
    verify(ts2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Succes_Twice()
  {
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);


    // Act
    boolean result;
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(ts1, times(1)).fire(1);
    verify(ts2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failed()
  {
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(false);
    when(ts2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    verify(ts1, times(1)).fire(1);
    verify(ts2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_PrimaryEmpty()
  {
    when(ts1.isEmpty()).thenReturn(true);
    when(ts2.isEmpty()).thenReturn(false);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(ts1, times(0)).fire(1);
    verify(ts2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_BothEmpty()
  {
    when(ts1.isEmpty()).thenReturn(true);
    when(ts2.isEmpty()).thenReturn(true);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    verify(ts1, times(0)).fire(1);
    verify(ts2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_SecondaryEmpty()
  {
    when(ts1.isEmpty()).thenReturn(false);
    when(ts2.isEmpty()).thenReturn(true);
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    boolean result;
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(ts1, times(2)).fire(1);
    verify(ts2, times(0)).fire(1);
  }
}

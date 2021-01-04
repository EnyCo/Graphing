import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardInput implements KeyListener 
{
     private static final int KEY_COUNT = 522;
     private enum State 
     {
         RELEASED,
         PRESSED,  
         ONCE      
     }
     private boolean[] currentKeys = null;
     
     private State[] keys = null;
        
  public KeyboardInput() {
    currentKeys = new boolean[ KEY_COUNT ];
    keys = new State[ KEY_COUNT ];
    for( int i = 0; i < KEY_COUNT; ++i ) 
    {
      keys[ i ] = State.RELEASED;
    }
  }
        
  public synchronized void poll() {
    for( int i = 0; i < KEY_COUNT; ++i ) {
      // Set the key state 
      if( currentKeys[ i ] ) {
        // If the key is down now, but was not
        // down last frame, set it to ONCE,
        // otherwise, set it to PRESSED
        if( keys[ i ] == State.RELEASED )
          keys[ i ] = State.ONCE;
        else
          keys[ i ] = State.PRESSED;
      } else {
        keys[ i ] = State.RELEASED;
      }
    }
  }
        
  public boolean keyDown( int keyCode ) 
  {
    return keys[ keyCode ] == State.ONCE ||keys[ keyCode ] == State.PRESSED;
  }
        
  public boolean keyDownOnce( int keyCode ) 
  {
    return keys[ keyCode ] == State.ONCE;
  }
        
  public synchronized void keyPressed( KeyEvent e ) {
    int keyCode = e.getKeyCode();
    if( keyCode >= 0 && keyCode < KEY_COUNT ) {
      currentKeys[ keyCode ] = true;
    }
  }

  public synchronized void keyReleased( KeyEvent e ) {
    int keyCode = e.getKeyCode();
    if( keyCode >= 0 && keyCode < KEY_COUNT ) {
      currentKeys[ keyCode ] = false;
    }
  }

  public void keyTyped( KeyEvent e ) 
  {
      //fill
  }
}
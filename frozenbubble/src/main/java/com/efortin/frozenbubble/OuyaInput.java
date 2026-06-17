/*
 *                 [[ Frozen-Bubble ]]
 *
 * OUYA port - controller key translation helper.
 *
 * This code is distributed under the GNU General Public License
 * version 2 or 3, as published by the Free Software Foundation.
 */

package com.efortin.frozenbubble;

import android.view.KeyEvent;

/**
 * OUYA controller key translation helper.
 * <p>The OUYA controller has no Start, Back or Menu keys, and its face
 * buttons generate <code>KEYCODE_BUTTON_*</code> events which standard
 * Android widgets (menu buttons, dialogs, option menus) do not treat as
 * navigation keys.  This translates the OUYA face buttons into the
 * navigation keys that both the user interface and the game engine
 * already understand:
 * <pre>
 *   O (BUTTON_A) -&gt; DPAD_CENTER  (confirm in menus, fire in game)
 *   A (BUTTON_B) -&gt; BACK         (cancel in menus, exit dialog in game)
 * </pre>
 * The translation is applied in {@code Activity.dispatchKeyEvent()} so the
 * rewritten event reaches the currently focused widget.  The original
 * device ID is preserved so that local 2 player games can still tell the
 * connected controllers apart.
 */
public final class OuyaInput {
  private OuyaInput() {
  }

  /**
   * @param keyCode the received controller key code.
   * @return the navigation key code the supplied controller key maps to,
   * or the original key code if no translation applies.
   */
  public static int mapKeyCode(int keyCode) {
    switch (keyCode) {
      case KeyEvent.KEYCODE_BUTTON_A:
        return KeyEvent.KEYCODE_DPAD_CENTER;
      case KeyEvent.KEYCODE_BUTTON_B:
        return KeyEvent.KEYCODE_BACK;
      default:
        return keyCode;
    }
  }

  /**
   * Translate OUYA controller buttons to navigation keys, preserving the
   * original event's device ID (required for local multiplayer).
   * @param event the received key event.
   * @return a rewritten event to dispatch, or the original event if no
   * translation applies.
   */
  public static KeyEvent translate(KeyEvent event) {
    int keyCode = event.getKeyCode();
    int mapped  = mapKeyCode(keyCode);
    if (mapped == keyCode) {
      return event;
    }
    return new KeyEvent(event.getDownTime(), event.getEventTime(),
                        event.getAction(), mapped, event.getRepeatCount(),
                        event.getMetaState(), event.getDeviceId(),
                        event.getScanCode(), event.getFlags(),
                        event.getSource());
  }
}

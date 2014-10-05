/**
 * LaBrisca 2011
 * @author Hector COsta Guzman
 */
import java.io.File;
import java.net.URL;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
public class MidiSound {
    public static Sequencer midiPlayer;
    public static void startMidi(URL midFilename) {
      try {
         Sequence song = MidiSystem.getSequence(midFilename);
         midiPlayer = MidiSystem.getSequencer();
         midiPlayer.open();
         midiPlayer.setSequence(song);
         midiPlayer.setLoopCount(0); // repeat 100 times (play once)
         midiPlayer.start();
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }
}
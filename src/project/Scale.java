package project;

import java.util.ArrayList;

public class Scale {
    private int length;
    private ArrayList<Note> notes;
    
    public Scale(int length, ArrayList<Note> notes) {
        this.length = length;
        this.notes = notes;
    }
    
    public Scale(int length) {
        this(length, new ArrayList<>());
    }
    
    public void addNote(Note newNote) {
        notes.add(newNote);
    }
    
    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    public int getLength() {
        return length;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } if (obj.getClass() != this.getClass()) {
            return false;
        }
        Scale scaleObj = (Scale)obj;
        if (scaleObj.getLength() != length) {
            return false;
        }
        for (int i = 0; i < length; i ++) {
            if (!(notes.get(i).equals(scaleObj.getNotes().get(i)))) {
                return false;
            }
        }
        return true;
    }
    
   @Override
   public String toString() {
       return notes.toString();
   }
    

}

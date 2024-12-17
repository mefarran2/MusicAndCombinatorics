package project;

public class Note {
    private String name;
    
    public Note(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } if (obj.getClass() != this.getClass()) {
            return false;
        }
        Note noteObj = (Note)obj;
        return this.name.equals(noteObj.toString());
    }
}

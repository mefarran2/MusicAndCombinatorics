package project;

import java.util.ArrayList;

public class ScaleList {
    private int listSize;
    private int scaleLength;
    private ArrayList<Scale> scales;

    private ArrayList<Note> dList;
    private ArrayList<Note> eList;
    private ArrayList<Note> fList;
    private ArrayList<Note> aList;
    private ArrayList<Note> bList;
    private ArrayList<Note[]> constraints;
    private ArrayList<Note> allNotes;

    public ScaleList(int scaleLength) {
        this.scaleLength = scaleLength;
        this.listSize = 0;
        this.scales = new ArrayList<Scale>();
        initLists();
    }


    private void initLists() {
        allNotes = new ArrayList<Note>();
        allNotes.add(new Note("C")); //C Natural
        allNotes.add(new Note("D1")); //D Flat
        allNotes.add(new Note("D2")); //D Natural
        allNotes.add(new Note("D3")); //D Sharp
        allNotes.add(new Note("E1")); //E Double flat
        allNotes.add(new Note("E2")); //E Flat
        allNotes.add(new Note("E3")); //E Natural
        allNotes.add(new Note("F1")); //F Natural
        allNotes.add(new Note("F2")); //F Sharp
        allNotes.add(new Note("G")); //G Natural
        allNotes.add(new Note("A1")); //A Flat
        allNotes.add(new Note("A2")); //A Natural
        allNotes.add(new Note("A3")); //A Sharp
        allNotes.add(new Note("B1")); //B Double Flat
        allNotes.add(new Note("B2")); //B Flat
        allNotes.add(new Note("B3")); //B Natural

        dList = new ArrayList<Note>();
        dList.add(allNotes.get(1));
        dList.add(allNotes.get(2));
        dList.add(allNotes.get(3));

        eList = new ArrayList<Note>();
        eList.add(allNotes.get(4));
        eList.add(allNotes.get(5));
        eList.add(allNotes.get(6));

        fList = new ArrayList<Note>();
        fList.add(allNotes.get(7));
        fList.add(allNotes.get(8));

        aList = new ArrayList<Note>();
        aList.add(allNotes.get(10));
        aList.add(allNotes.get(11));
        aList.add(allNotes.get(12));

        bList = new ArrayList<Note>();
        bList.add(allNotes.get(13));
        bList.add(allNotes.get(14));
        bList.add(allNotes.get(15));

        Note[] const1 = { allNotes.get(2), allNotes.get(4) }; // D2 and E1
        Note[] const2 = { allNotes.get(3), allNotes.get(5) }; // D3 and E2
        Note[] const3 = { allNotes.get(3), allNotes.get(4) }; // D3 and E1
        Note[] const4 = { allNotes.get(11), allNotes.get(13) }; // A2 and B1
        Note[] const5 = { allNotes.get(12), allNotes.get(14) }; // A3 and B2
        Note[] const6 = { allNotes.get(12), allNotes.get(13) }; // A3 and B1
        constraints = new ArrayList<Note[]>();
        constraints.add(const1);
        constraints.add(const2);
        constraints.add(const3);
        constraints.add(const4);
        constraints.add(const5);
        constraints.add(const6);

    }


    public ArrayList<Note[]> getConststraints() {
        return constraints;
    }


    public ArrayList<Scale> getScales() {
        return scales;
    }


    public void printScales() {
        printScales(scales);
    }


    public void printScales(ArrayList<Scale> list) {
        for (int i = 1; i < list.size() + 1; i++) {
            if ((i % 4) == 0) {
                System.out.println(list.get(i - 1));
            }
            else {
                if (i == list.size()) {
                    System.out.println(list.get(i - 1) + "\t");
                }
                else {
                    System.out.print(list.get(i - 1) + "\t");
                }
            }
        }
    }


    // returns a list of all scales of a given length with no restrictions
    public void generateScales() {
        boolean finished = false;
        int overallCount = 1;
        int dIndex = 0;
        int eIndex = 0;
        int fIndex = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (!finished) {
            ArrayList<Note> newNoteList = new ArrayList<>();
            newNoteList.add(new Note("C")); // C is the base note
            newNoteList.add(dList.get(dIndex));
            newNoteList.add(eList.get(eIndex));
            newNoteList.add(fList.get(fIndex));
            newNoteList.add(new Note("G"));
            newNoteList.add(aList.get(aIndex));
            newNoteList.add(bList.get(bIndex));

            Scale newScale = new Scale(7, newNoteList);
            scales.add(newScale);
            listSize++;

            bIndex++;
            if (overallCount >= 3 && overallCount % 3 == 0) {
                aIndex++;
                bIndex = 0;
            }
            if (overallCount >= 9 && overallCount % 9 == 0) {
                fIndex++;
                aIndex = 0;
            }
            if (overallCount >= 18 && overallCount % 18 == 0) {
                eIndex++;
                fIndex = 0;
            }
            if (overallCount >= 54 && overallCount % 54 == 0) {
                dIndex++;
                eIndex = 0;
            }
            if (dIndex == 3 && (eIndex + aIndex + aIndex + bIndex + bIndex == 0)) {
                finished = true;
            }
            overallCount++;
        }
        listSize = overallCount - 1;
    }


    public ArrayList<Scale> enforceRestrictions(int numRestrictions) {
        if (numRestrictions == 1) {
            return constrainByOne();
        }
        else {
            return constrainByGreaterThanOne(numRestrictions);
        }
    }


    private ArrayList<Scale> constrainByOne() {
        ArrayList<Scale> rejectedList = new ArrayList<>();
        for (int j = 0; j < scales.size(); j++) {
            ArrayList<Note> currNoteList = scales.get(j).getNotes();
            for (int i = 0; i < constraints.size(); i++) {
                Note[] currConstraint = constraints.get(i);
                if (noteListContrainted(currNoteList, currConstraint)) {
                    rejectedList.add(scales.get(j));
                }
            }
        }
        return rejectedList;
    }


    private ArrayList<Scale> constrainByGreaterThanOne(int numConstraints) {
        ArrayList<Scale> rejectedList = new ArrayList<>();
        for (int j = 0; j < scales.size(); j++) {
            ArrayList<Note> currNoteList = scales.get(j).getNotes();
            int validCounter = 0;
            for (int i = 0; i < constraints.size(); i++) {
                Note[] currConstraint = constraints.get(i);
                if (noteListContrainted(currNoteList, currConstraint)) {
                    validCounter++;
                }
            }
            if (validCounter == numConstraints) {
                rejectedList.add(scales.get(j));
            }
        }
        return rejectedList;
    }


    private boolean noteListContrainted(ArrayList<Note> noteList, Note[] constraint) {
        return noteList.contains(constraint[0]) && noteList.contains(constraint[1]);
    }
    
    public ArrayList<Scale> removeRejectedScales(ArrayList<Scale> list) {
        ArrayList<Scale> rtnScales = new ArrayList<>();
        for (int i = 0; i < scales.size(); i ++) {
            if (!list.contains(scales.get(i))) {
                rtnScales.add(scales.get(i));
            }
        }
        return rtnScales;
    }
    
    public ArrayList<Scale> getDecendingScales(ArrayList<Scale> list) {
        ArrayList<Scale> rtnScales = new ArrayList<>();
        for (int i = 0; i < list.size(); i ++) {
            rtnScales.add(reverseScale(list.get(i)));
        }
        return rtnScales;
    }
    
    private Scale reverseScale(Scale scale) {
        Scale reveredScale = new Scale(7);
        for (int i = scale.getLength()-1; i >= 0; i --) {
            reveredScale.addNote(scale.getNotes().get(i));
        }
        return reveredScale;
    }

}

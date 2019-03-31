package com.example.dress.util.ShowLetter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class AllShowLetter extends LitePalSupport implements Serializable {
    List<ShowLetterGroup> showLetterGroups;

    public AllShowLetter(List<ShowLetterGroup> showLetterGroups) {
        this.showLetterGroups = showLetterGroups;
    }

    public List<ShowLetterGroup> getShowLetterGroups() {
        return showLetterGroups;
    }

    public void setShowLetterGroups(List<ShowLetterGroup> showLetterGroups) {
        this.showLetterGroups = showLetterGroups;
    }
}

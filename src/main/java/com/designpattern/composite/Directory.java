package com.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

// Composite (grup)
class Directory implements FileSystemItem {

    private final List<FileSystemItem> items = new ArrayList<>();

    void add(FileSystemItem item) {
        items.add(item);
    }

    @Override
    public int size() {
        return items.stream()
                .mapToInt(FileSystemItem::size)
                .sum();
    }
}

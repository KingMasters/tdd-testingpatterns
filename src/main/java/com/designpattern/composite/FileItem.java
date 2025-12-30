package com.designpattern.composite;
// Leaf (tekil bileşen)
class FileItem implements FileSystemItem {

    private final int size;

    FileItem(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }
}

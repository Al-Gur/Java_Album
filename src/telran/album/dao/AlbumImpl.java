package telran.album.dao;

import telran.album.model.Photo;

import java.time.LocalDate;
import java.util.Arrays;

public class AlbumImpl implements Album {
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        if (photo == null || photos.length == size || getPhotoFromAlbum(photo.getPhotoId(), photo.getAlbumId()) != null) {
            return false;
        }
        photos[size++] = photo;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
                photos[i] = photos[size-1];
                photos[--size]=null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
                photos[i].setUrl(url);
                return true;
            }
        }
        return false;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId && photos[i].getPhotoId() == photoId) {
                return photos[i];
            }
        }
        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        Photo[] temp=new Photo[size];
        int j=0;
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId) {
                temp[j++] = photos[i];
            }
        }
        return Arrays.copyOf(temp, j);
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        Photo[] temp=new Photo[size];
        int j=0;
        for (int i = 0; i < size; i++) {
            LocalDate date = photos[i].getDate().toLocalDate();
            if (!date.isBefore(dateFrom) && date.isBefore(dateTo)) {
                temp[j++] = photos[i];
            }
        }
        return Arrays.copyOf(temp, j);
    }

    @Override
    public int size() {
        return size;
    }
}
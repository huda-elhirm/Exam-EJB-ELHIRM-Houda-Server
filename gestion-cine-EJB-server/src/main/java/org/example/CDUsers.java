package org.example;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CDUsers {

    public List<CD> listAvailableCDs();
    public List<CD> listBorrowedCDs(Long userId);
    public void borrowCD(Long userId, Long cdId);
    public void returnCD(Long cdId);

}

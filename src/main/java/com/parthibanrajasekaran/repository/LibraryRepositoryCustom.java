package com.parthibanrajasekaran.repository;

import com.parthibanrajasekaran.controller.Library;
import java.util.List;

public interface LibraryRepositoryCustom {

  List<Library> findAllByAuthor(String authorName);

}

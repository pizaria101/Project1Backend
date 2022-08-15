package dev.schulte.daos;

import dev.schulte.entities.Constituent;

import java.util.List;

public interface ConstituentDAO {

    Constituent createConstituent(Constituent constituent);

    List<Constituent> getAllConstituents();

    Constituent updateConstituent(Constituent constituent);

}

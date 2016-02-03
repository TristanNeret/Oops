/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.LegalInformation;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bibo
 */
@Remote
public interface LegalInformationBean {
    // the legal informations about an contractor siret, siren, rcs, insurrance
    public void register(LegalInformation linfo);
    public List<LegalInformation> findAll();
    public LegalInformation findById(long id);
    public void update(LegalInformation linfo);
    public void delete(LegalInformation linfo);
}

/*
 *************************************************************************
 * Copyright 2013 DFG SPP 1593 (http://dfg-spp1593.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************************************************************
 */

package org.cocome.tradingsystem.inventory.data.plant.productionunit;

import org.cocome.tradingsystem.inventory.application.IIdentifiableTO;
import org.cocome.tradingsystem.inventory.application.plant.PlantTO;
import org.cocome.tradingsystem.inventory.data.IIdentifiable;

import javax.xml.bind.annotation.*;

/**
 * This class represents a Product in the database
 *
 * @author Rudolf Biczok
 */
public interface IProductionUnit extends IIdentifiable {

    /**
     * @return Production unit location.
     */
     String getLocation();

    /**
     * @param location Production unit location
     */
    void setLocation(final String location);

    /**
     * @return The URL location used to communicate with the device
     */
    String getInterfaceUrl();

    /**
     * @param interfaceUrl The URL location used to communicate with the device
     */
    void setInterfaceUrl(final String interfaceUrl);

    /**
     * @return the production unit class
     */
    IProductionUnitClass getProductionUnitClass();

    /**
     * @param productionUnitClass the production unit class
     */
    void setProductionUnitClass(IProductionUnitClass productionUnitClass);

    /**
     * @return the plant that owns this production unit
     */
    PlantTO getPlant();

    /**
     * @param plant the plant that owns this production unit
     */
    void setPlant(PlantTO plant);
}

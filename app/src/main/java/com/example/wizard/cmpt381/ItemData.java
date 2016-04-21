package com.example.wizard.cmpt381;

/*
 * This file is part of the Eyedeas project - https://github.com/leftea/cmpt381
 * Copyright (c) 2016 Simone Debrowney & Matthew Galbraith
 *
 * Eyedeas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Eyedeas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Eyedeas.  If not, see <http://www.gnu.org/licenses/>.
 */

public class ItemData {
    Integer imageId;

    public ItemData(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }
}


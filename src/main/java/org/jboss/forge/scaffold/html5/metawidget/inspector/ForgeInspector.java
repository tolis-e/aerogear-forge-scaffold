/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.forge.scaffold.html5.metawidget.inspector;

import org.metawidget.inspector.impl.BaseObjectInspector;
import org.metawidget.inspector.impl.BaseObjectInspectorConfig;
import org.metawidget.inspector.impl.propertystyle.Property;
import org.metawidget.util.CollectionUtils;

import javax.persistence.*;
import java.util.Map;

import static org.jboss.forge.scaffold.html5.metawidget.inspector.ForgeInspectionResultConstants.*;
import static org.metawidget.inspector.InspectionResultConstants.TRUE;

/**
 * Inspects Forge-specific metadata.
 *
 * @author Richard Kennard
 */

public class ForgeInspector
        extends BaseObjectInspector {
    //
    // Constructor
    //

    public ForgeInspector() {
        this(new BaseObjectInspectorConfig());
    }

    public ForgeInspector(BaseObjectInspectorConfig config) {
        super(config);
    }

    //
    // Protected methods
    //

    @Override
    protected Map<String, String> inspectProperty(Property property)
            throws Exception {
        Map<String, String> attributes = CollectionUtils.newHashMap();

        // OneToOne

        if (property.isAnnotationPresent(OneToOne.class) || property.isAnnotationPresent(Embedded.class)) {

            attributes.put(ONE_TO_ONE, TRUE);
        }

        // ManyToOne

        if (property.isAnnotationPresent(ManyToOne.class)) {
            attributes.put(MANY_TO_ONE, TRUE);
        }

        // OneToMany and ManyToMany

        if (property.isAnnotationPresent(OneToMany.class) || property.isAnnotationPresent(ManyToMany.class)) {
            attributes.put(N_TO_MANY, TRUE);
        }

        return attributes;
    }
}

package com.exemplo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "grid-columns-config")
public class GridColumnsConfig {

    private List<GridColumnConfig> columns = new ArrayList<>();

    @XmlElementWrapper(name = "columns")  // <<< ESTA LINHA QUE FALTAVA
    @XmlElement(name = "column")
    public List<GridColumnConfig> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumnConfig> columns) {
        this.columns = columns;
    }
}

module ru.suvorov.graphiox {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.suvorov.graphiox to javafx.fxml;
    exports ru.suvorov.graphiox;
}
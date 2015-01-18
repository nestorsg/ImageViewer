package App;

import Controller.Command;
import Controller.NextImageCommand;
import Controller.PrevImageCommand;
import View_Swing.ApplicationFrame;
import Model.Image;
import View_Persisntent.ImageListLoader;
import View_Persistent_File.FileImageListLoader;
import View_Swing.ActionListenerFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        new Application().execute();
    }
    
    private static final String PATH = "C:\\Users\\Nestor\\Desktop\\IS2\\ImageViewer";
    private Map<String, Command> commandMap;
    private ApplicationFrame frame;

    private void execute() {
        ImageListLoader loader = createImageListLoader(PATH);
        List<Image> list = loader.load();
        frame = new ApplicationFrame(createActionListenerFactory());
        frame.getImageViewer().setImage(list.get(0));
        createCommands();
        frame.setVisible(true);
    }

    private void createCommands() {
        commandMap = new HashMap<>();
        commandMap.put("Next", new NextImageCommand(frame.getImageViewer()));
        commandMap.put("Prev", new PrevImageCommand(frame.getImageViewer()));
    }

    private ImageListLoader createImageListLoader(String path) {
        return new FileImageListLoader(path);
    }

    private ActionListenerFactory createActionListenerFactory() {
        return new ActionListenerFactory() {

            @Override
            public ActionListener create(final String action) {
                return new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Command command = commandMap.get(action);
                        if (command == null) return;
                        command.execute();
                    }
                };
            }
        };
    }  
}

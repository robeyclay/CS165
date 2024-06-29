public class GameController {
    public GameView view;
    public GameData data;
    public CombatEngine engine;

    public GameController(GameData data, GameView view, CombatEngine engine){
        this.data = data;
        this.view = view;
        this.engine = engine;
    }
    public void start(){  //here
        System.out.println("start");
        //engine.initialize();
        view.displayMainMenu();
    }

    protected boolean processCommand(String command){
        boolean rtn = true;
        if(command.contains("bye") || command.contains("exit")){
            rtn = false;
        } else if (command.equalsIgnoreCase("ls") || command.equalsIgnoreCase("list all")) {
            view.listKnights(data.getKnights());
        } else if (command.equalsIgnoreCase("list active")){
            data.getActiveKnights();
        } else if(command.contains("show")){
            String identifier = command.substring(3);
            for(Knight checker: data.knights){
                if(identifier.equalsIgnoreCase(checker.getName()) || identifier.equalsIgnoreCase(""+ checker.getId())){
                    System.out.println(checker.toString());
                }
            }
        } else if (command.contains("remove")){
            String identifier = command.substring(5);
            for(Knight checker: data.activeKnights){
                if(identifier.equalsIgnoreCase(checker.getName()) || identifier.equalsIgnoreCase(""+ checker.getId())){
                    data.removeActive(checker);
                }
            }
        } else if (command.contains("save")){
            data.save("saveData.csv");
        } else if (command.contains("explore") || command.contains("adventure") || command.contains("quest")){
            engine.initialize();
            engine.runCombat();
            engine.clear();
        } else {
            view.printHelp();
        }
        return rtn;
    }
}

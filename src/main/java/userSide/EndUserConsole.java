package userSide;

import business.IEndUserRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.logging.Logger;

public class EndUserConsole {
    private static final Logger LOGGER= Logger.getLogger(EndUserConsole.class.getSimpleName());
    private IEndUserRequest endUserRequest;

    public EndUserConsole(IEndUserRequest endUserRequest) {
        this.endUserRequest= endUserRequest;
    }

    public void launchUserInterfaceInteraction() {
        CLI("please enter <send> command line, in order to send birthday emails!");
        LOGGER.warning("done.");
    }

    private void CLI(String commandLine) {
        LOGGER.info(commandLine);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(!line.trim().toLowerCase().equals("send")) {
            CLI("please type send");
        }else {
            int sentMessages = endUserRequest.send();
            LOGGER.info("total sent messages for today: "
                    + LocalDate.now(ZoneId.systemDefault()).toString()
                    +" are: "+sentMessages);
        }
    }
}

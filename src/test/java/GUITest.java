import static org.junit.Assert.assertThat;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import com.athaydes.automaton.Swinger;

import model.FrameModel;
import model.PersonModel;
import view.CreatePeopleView;
import view.GanttChartGUI;
import view.MainWindow;
public class GUITest {

	@Test
	public void testSchedule() throws IOException, InterruptedException {
		
		MainWindow.main(new String[0]);
		Swinger swinger = Swinger.forSwingWindow();
		swinger.pause(1000);
		
		JList people = (JList)swinger.getAt("name:people");
		JList tasks = (JList)swinger.getAt("name:tasks");		
		
		swinger.doubleClickOn("name:personNameInput").type("Adriel").moveBy(0, 130).click().clickOn("name:addTeamMember").clickOn("name:people").pause(250);
		assertThat(String.valueOf(people.getModel().getElementAt(0)),equalTo("Name: Adriel Skills: Java "));
		swinger.doubleClickOn("name:personNameInput").type("Tomas").moveBy(0, 150).click().clickOn("name:addTeamMember").clickOn("name:people").pause(250);
		assertThat(String.valueOf(people.getModel().getElementAt(1)),equalTo("Name: Tomas Skills: CPlus "));
		swinger.clickOn("name:rightButton").pause(250).doubleClickOn("name:taskNameInput").type("AgileTest").moveBy(-260, 110).click().moveBy(0, 100).click().moveBy(0, 50).click().clickOn("name:addTask").pause(250);
		assertThat(String.valueOf(tasks.getModel().getElementAt(0)),equalTo("Name: AgileTest Effort: 2 Number of People: 2 Skills: Java Tasks required: None"));
		swinger.clickOn("name:rightButton").pause(250).clickOn("name:generate").pause(1000);
		assertThat(String.valueOf(swinger.getAt("name:schedule").isVisible()), equalTo("true"));
		
	}
}
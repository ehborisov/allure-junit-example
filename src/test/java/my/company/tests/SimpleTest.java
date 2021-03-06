package my.company.tests;

import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 24.11.13
 */
public class SimpleTest {

    @Test
    @TestCaseId("TC-3")
    public void simpleTest() throws Exception {
        saveImgAttachment("img3.jpg");
        assertThat(2, is(2));
    }

    @Step
    public void checkThat2is2() {
        assertThat(2, is(2));
    }

    @Test
    public void simpleTestWithSteps() throws Exception {
        checkThat2is2();
    }

    @Attachment
    public String makeAttach() {
        return "yeah, 2 is 2";
    }

    @Test
    @TestCaseId("TC-2")
    @Issue("ID-2")
    public void simpleTestWithAttachments() throws Exception {
        assertThat(2, is(2));
        makeAttach();
        saveImgAttachment("img2.jpg");
    }

    @Test
    public void csvAttachmentTest() throws Exception {
        saveCsvAttachment();
        saveImgAttachment("img1.jpg");
    }

    @Attachment(value = "Sample csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("sample.csv");
        if (resource == null) {
            fail("Couldn't find resource 'sample.csv'");
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

    @Attachment(value = "Sample heavy img attachment", type = "image/jpeg")
    public byte[] saveImgAttachment(String res) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource(res);
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }
}

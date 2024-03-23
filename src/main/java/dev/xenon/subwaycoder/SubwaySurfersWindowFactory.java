package dev.xenon.subwaycoder;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

final class SubwaySurfersWindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        SubwaySurfersToolWindowContent toolWindowContent = new SubwaySurfersToolWindowContent(toolWindow);

        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        Content content = factory.createContent(toolWindowContent.getContentPanel(), "AttentionKeeper", false);

        ContentManager manager = toolWindow.getContentManager();
        manager.addContent(content);
    }

    private static class SubwaySurfersToolWindowContent {

        private static final String SUBWAY_SURFERS_GIF_PATH = "/subwaySurfersWindow/subway-surfers.gif";
        private final JPanel contentPanel = new JPanel();
        private final JLabel gifLabel = new JLabel();

        public SubwaySurfersToolWindowContent(ToolWindow toolWindow) {
            contentPanel.setLayout(new BorderLayout(0, 20));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            contentPanel.add(createPanel(), BorderLayout.PAGE_START);
        }

        @NotNull
        private JPanel createPanel() {
            JPanel panel = new JPanel();
            setIconLabel(gifLabel, SUBWAY_SURFERS_GIF_PATH);
            panel.add(gifLabel);
            return panel;
        }

        private void setIconLabel(JLabel label, String imagePath) {
            label.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))));
        }

        public JPanel getContentPanel() {
            return contentPanel;
        }

    }

}
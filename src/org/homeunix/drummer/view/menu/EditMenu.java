/*
 * Created on Apr 8, 2007 by wyatt
 */
package org.homeunix.drummer.view.menu;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import net.roydesign.app.Application;
import net.roydesign.app.PreferencesJMenuItem;
import net.roydesign.ui.JScreenMenu;
import net.roydesign.ui.JScreenMenuItem;

import org.homeunix.drummer.controller.Translate;
import org.homeunix.drummer.controller.TranslateKeys;
import org.homeunix.drummer.controller.menu.EditMenuController;
import org.homeunix.drummer.prefs.PrefsInstance;
import org.homeunix.drummer.view.MainFrame;
import org.homeunix.drummer.view.TransactionsFrame;
import org.homeunix.thecave.moss.gui.abstractwindows.AbstractFrame;

public class EditMenu extends JScreenMenu {
	public static final long serialVersionUID = 0;
	private final EditMenuController controller;
	
	public EditMenu(AbstractFrame frame) {
		controller = new EditMenuController(frame);
		
		this.setText(Translate.getInstance().get(TranslateKeys.MENU_EDIT));
		
		final JScreenMenuItem toggleReconciled = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_TOGGLE_RECONCILED));
		final JScreenMenuItem toggleCleared = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_TOGGLE_CLEARED));
		final JScreenMenuItem editAutomaticTransactions = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_SCHEDULED_ACTIONS));
		final JScreenMenuItem clearTransaction = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_CLEAR_NEW));
		final JScreenMenuItem recordTransaction = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_RECORD_UPDATE));
		final JScreenMenuItem unrollAll = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_UNROLL_ALL));
		final JScreenMenuItem rollAll = new JScreenMenuItem(Translate.getInstance().get(TranslateKeys.MENU_EDIT_ROLL_ALL));
		final PreferencesJMenuItem preferences = Application.getInstance().getPreferencesJMenuItem();
		preferences.setText(Translate.getInstance().get(TranslateKeys.MENU_EDIT_PREFERENCES));

		toggleReconciled.setActionCommand(TranslateKeys.MENU_EDIT_TOGGLE_RECONCILED.toString());
		toggleCleared.setActionCommand(TranslateKeys.MENU_EDIT_TOGGLE_CLEARED.toString());
		editAutomaticTransactions.setActionCommand(TranslateKeys.MENU_EDIT_SCHEDULED_ACTIONS.toString());
		clearTransaction.setActionCommand(TranslateKeys.MENU_EDIT_CLEAR_NEW.toString());
		recordTransaction.setActionCommand(TranslateKeys.MENU_EDIT_RECORD_UPDATE.toString());
		preferences.setActionCommand(TranslateKeys.MENU_EDIT_PREFERENCES.toString());
		unrollAll.setActionCommand(TranslateKeys.MENU_EDIT_UNROLL_ALL.toString());
		rollAll.setActionCommand(TranslateKeys.MENU_EDIT_ROLL_ALL.toString());

		toggleReconciled.addActionListener(controller);
		toggleCleared.addActionListener(controller);
		editAutomaticTransactions.addActionListener(controller);
		clearTransaction.addActionListener(controller);
		recordTransaction.addActionListener(controller);
		preferences.addActionListener(controller);
		unrollAll.addActionListener(controller);
		rollAll.addActionListener(controller);
		
		toggleReconciled.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				KeyEvent.SHIFT_MASK + Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		toggleCleared.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.SHIFT_MASK + Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		clearTransaction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		recordTransaction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		
		if (frame instanceof TransactionsFrame){
			this.add(recordTransaction);
			this.add(clearTransaction);
			if (PrefsInstance.getInstance().getPrefs().isShowAdvanced()){
				this.addSeparator();
				this.add(toggleCleared);
				this.add(toggleReconciled);
			}
		}
		if (frame instanceof MainFrame){
			//TODO This was proving too difficult to fit in the 2.4 release; add it in 2.5.
//			this.add(rollAll);
//			this.add(unrollAll);			
//			this.addSeparator();
			this.add(editAutomaticTransactions);
		}
		if (!PreferencesJMenuItem.isAutomaticallyPresent()){
			this.addSeparator();
			this.add(preferences);
		}
	}
}

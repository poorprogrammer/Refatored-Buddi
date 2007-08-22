/*
 * Created on Aug 12, 2007 by wyatt
 */
package org.homeunix.thecave.buddi.plugin.api.model.immutable;

import java.util.Date;
import java.util.List;

import org.homeunix.thecave.buddi.i18n.keys.BudgetPeriodKeys;
import org.homeunix.thecave.buddi.model.Account;
import org.homeunix.thecave.buddi.model.BudgetCategory;
import org.homeunix.thecave.buddi.model.BudgetPeriod;
import org.homeunix.thecave.buddi.model.DataModel;
import org.homeunix.thecave.buddi.model.Source;
import org.homeunix.thecave.buddi.model.Transaction;
import org.homeunix.thecave.buddi.model.Type;
import org.homeunix.thecave.buddi.model.WrapperLists;

public class ImmutableModel {
	private final DataModel model;
	
	public ImmutableModel(DataModel model) {
		this.model = model;
	}
	
	
	public BudgetPeriodKeys getPeriodType(){
		return getModel().getPeriodType();
	}
	
	protected DataModel getModel(){
		return model;
	}
	
	public List<ImmutableAccount> getAccounts(){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableAccount, Account>(getModel(), getModel().getAccounts());
	}
	
	public List<ImmutableBudgetCategory> getBudgetCategories(){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableBudgetCategory, BudgetCategory>(getModel(), getModel().getBudgetCategories());
	}
	
	public List<ImmutableType> getTypes(){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableType, Type>(getModel(), getModel().getTypes());		
	}
	
	public List<ImmutableTransaction> getTransactions(){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableTransaction, Transaction>(getModel(), getModel().getTransactions());
	}
	
	public List<ImmutableTransaction> getTransactions(ImmutableSource source){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableTransaction, Transaction>(getModel(), getModel().getTransactions((Source) source.getRaw()));
	}
	
	public List<ImmutableTransaction> getTransactions(Date startDate, Date endDate){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableTransaction, Transaction>(getModel(), getModel().getTransactions(startDate, endDate));
	}
	
	public List<ImmutableTransaction> getTransactions(ImmutableSource source, Date startDate, Date endDate){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableTransaction, Transaction>(getModel(), getModel().getTransactions((Source) source.getRaw(), startDate, endDate));
	}
	
	public List<ImmutableBudgetPeriod> getBudgetPeriodsInRange(Date startDate, Date endDate){
		return new WrapperLists.ImmutableObjectWrapperList<ImmutableBudgetPeriod, BudgetPeriod>(getModel(), getModel().getBudgetPeriodsInRange(startDate, endDate));
	}
}

/***************************************************************************
 * Copyright 2013 DFG SPP 1593 (http://dfg-spp1593.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.cocome.tradingsystem.cashdeskline.cashdesk.cardreader;

import javax.ejb.Local;

/**
 * Defines card reader model actions that can be triggered from outside.
 * 
 * @author Lubomir Bulej
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
@Local
public interface ICardReaderModel {

	/**
	 * Sends the event to notify other components that the
	 * user entered his credit card info.
	 * 
	 * @param cardInfo
	 * 		the card info entered
	 */
	void sendCreditCardInfo(String cardInfo);

	/**
	 * Notifies other components via an event that the
	 * user entered his credit card pin
	 * 
	 * @param pin
	 * 		the pin entered
	 */
	void sendCreditCardPin(int pin);

}

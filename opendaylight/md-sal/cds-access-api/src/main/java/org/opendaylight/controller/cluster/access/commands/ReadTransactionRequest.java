/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cluster.access.commands;

import akka.actor.ActorRef;
import com.google.common.annotations.Beta;
import javax.annotation.Nonnull;
import org.opendaylight.controller.cluster.access.ABIVersion;
import org.opendaylight.controller.cluster.access.concepts.TransactionIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;

/**
 * A transaction request to read a particular path exists in the current view of a particular transaction.
 *
 * @author Robert Varga
 */
@Beta
public final class ReadTransactionRequest extends AbstractReadTransactionRequest<ReadTransactionRequest> {
    private static final long serialVersionUID = 1L;

    public ReadTransactionRequest(final @Nonnull TransactionIdentifier identifier, final long sequence,
            final @Nonnull ActorRef replyTo, final @Nonnull YangInstanceIdentifier path) {
        this(identifier, sequence, 0, replyTo, path);
    }

    ReadTransactionRequest(final @Nonnull TransactionIdentifier identifier, final long sequence,
            final long retry, final @Nonnull ActorRef replyTo, final @Nonnull YangInstanceIdentifier path) {
        super(identifier, sequence, retry, replyTo, path);
    }

    private ReadTransactionRequest(final ReadTransactionRequest request, final ABIVersion version) {
        super(request, version);
    }

    private ReadTransactionRequest(final ReadTransactionRequest request, final long retry) {
        super(request, retry);
    }

    @Override
    protected ReadTransactionRequest cloneAsRetry(final long retry) {
        return new ReadTransactionRequest(this, retry);
    }

    @Override
    protected ReadTransactionRequest cloneAsVersion(final ABIVersion version) {
        return new ReadTransactionRequest(this, version);
    }

    @Override
    protected ReadTransactionRequestProxyV1 externalizableProxy(final ABIVersion version) {
        return new ReadTransactionRequestProxyV1(this);
    }
}
